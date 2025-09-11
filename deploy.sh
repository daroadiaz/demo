#!/bin/bash
set -e

RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m'

print_message() {
    echo -e "${GREEN}[INFO]${NC} $1"
}

print_error() {
    echo -e "${RED}[ERROR]${NC} $1"
}

print_warning() {
    echo -e "${YELLOW}[WARNING]${NC} $1"
}

check_docker() {
    if ! command -v docker &> /dev/null; then
        print_error "Docker no está instalado. Por favor, instala Docker primero."
        exit 1
    fi
    
    if ! command -v docker-compose &> /dev/null; then
        print_error "Docker Compose no está instalado. Por favor, instala Docker Compose primero."
        exit 1
    fi
    
    print_message "Docker y Docker Compose están instalados correctamente."
}

check_wallet() {
    WALLET_PATH="./src/main/resources/wallet"
    
    if [ ! -d "$WALLET_PATH" ]; then
        print_error "No se encuentra el directorio del wallet en: $WALLET_PATH"
        print_warning "Asegúrate de tener el wallet de Oracle Cloud en la ubicación correcta."
        exit 1
    fi
    
    if [ ! -f "$WALLET_PATH/tnsnames.ora" ]; then
        print_warning "No se encuentra tnsnames.ora en el wallet"
    fi
    
    if [ ! -f "$WALLET_PATH/truststore.jks" ]; then
        print_error "No se encuentra truststore.jks en el wallet"
        exit 1
    fi
    
    if [ ! -f "$WALLET_PATH/keystore.jks" ]; then
        print_error "No se encuentra keystore.jks en el wallet"
        exit 1
    fi
    
    print_message "Wallet de Oracle verificado correctamente."
}

create_docker_properties() {
    DOCKER_PROPS="./src/main/resources/application-docker.properties"
    
    if [ ! -f "$DOCKER_PROPS" ]; then
        print_message "Creando archivo application-docker.properties..."
        print_message "Por favor, crea el archivo application-docker.properties en src/main/resources/"
    fi
}

build() {
    print_message "Iniciando construcción de la imagen Docker..."
    check_wallet
    create_docker_properties
    
    docker-compose build --no-cache
    
    if [ $? -eq 0 ]; then
        print_message "Imagen construida exitosamente."
    else
        print_error "Error al construir la imagen."
        exit 1
    fi
}

up() {
    print_message "Levantando servicios..."
    
    docker-compose up -d
    
    if [ $? -eq 0 ]; then
        print_message "Servicios levantados exitosamente."
        print_message "Esperando a que el servicio esté listo..."
        sleep 10
        
        health_check
    else
        print_error "Error al levantar los servicios."
        exit 1
    fi
}

down() {
    print_message "Deteniendo servicios..."
    
    docker-compose down
    
    if [ $? -eq 0 ]; then
        print_message "Servicios detenidos exitosamente."
    else
        print_error "Error al detener los servicios."
        exit 1
    fi
}

restart() {
    print_message "Reiniciando servicios..."
    down
    up
}

logs() {
    print_message "Mostrando logs del servicio spring-bff..."
    docker-compose logs -f spring-bff
}

status() {
    print_message "Estado de los servicios:"
    docker-compose ps
    
    echo ""
    print_message "Uso de recursos:"
    docker stats --no-stream spring-bff-service
}

health_check() {
    print_message "Verificando salud del servicio..."
    
    MAX_ATTEMPTS=30
    ATTEMPT=0
    
    while [ $ATTEMPT -lt $MAX_ATTEMPTS ]; do
        if curl -f http://localhost:8080/test-connection &> /dev/null; then
            print_message "Servicio saludable y respondiendo correctamente."
            echo ""
            print_message "URLs disponibles:"
            echo "  - Health Check: http://localhost:8080/test-connection"
            echo "  - API Productos: http://localhost:8080/api/v1/productos"
            echo "  - API Bodegas: http://localhost:8080/api/v1/bodegas"
            return 0
        else
            ATTEMPT=$((ATTEMPT + 1))
            echo -n "."
            sleep 2
        fi
    done
    
    print_error "El servicio no responde después de $MAX_ATTEMPTS intentos."
    print_warning "Verifica los logs con: ./deploy.sh logs"
    return 1
}

clean() {
    print_warning "Esta acción eliminará todos los contenedores, imágenes y volúmenes del proyecto."
    read -p "¿Estás seguro? (y/N): " -n 1 -r
    echo
    
    if [[ $REPLY =~ ^[Yy]$ ]]; then
        print_message "Limpiando todo..."
        docker-compose down -v --rmi all
        print_message "Limpieza completada."
    else
        print_message "Operación cancelada."
    fi
}

check_docker

case "${1:-}" in
    build)
        build
        ;;
    up)
        up
        ;;
    down)
        down
        ;;
    restart)
        restart
        ;;
    logs)
        logs
        ;;
    status)
        status
        ;;
    health)
        health_check
        ;;
    clean)
        clean
        ;;
    *)
        echo "Uso: $0 {build|up|down|restart|logs|status|health|clean}"
        echo ""
        echo "Comandos:"
        echo "  build   - Construir la imagen Docker"
        echo "  up      - Levantar los servicios en modo detached"
        echo "  down    - Detener los servicios"
        echo "  restart - Reiniciar los servicios"
        echo "  logs    - Ver logs del servicio"
        echo "  status  - Ver estado de los servicios"
        echo "  health  - Verificar salud del servicio"
        echo "  clean   - Limpiar todo (contenedores, imágenes, volúmenes)"
        echo ""
        echo "Ejemplo de uso completo:"
        echo "  $0 build"
        echo "  $0 up"
        exit 1
        ;;
esac