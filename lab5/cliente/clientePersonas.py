import sys
import datetime
import configparser
import requests
from requests.structures import CaseInsensitiveDict
import datetime
from datetime import timedelta


#Variables globales para verificacion
api_personas_url_base = None
archivo_config = 'ConfigFile.properties'

def cargar_variables():
    config = configparser.RawConfigParser()
    config.read(archivo_config)

    global api_personas_url_listar, api_personas_url_crear, api_personas_url_cuotareset, api_personas_url_cuota, api_personas_url_seccion, api_personas_url_nivel, api_personas_url_grado
    api_personas_url_listar = config.get('SeccionApi', 'api_personas_url_listar')
    api_personas_url_crear = config.get('SeccionApi', 'api_personas_url_crear')
    api_personas_url_cuotareset = config.get('SeccionApi','api_personas_url_cuotareset')
    api_personas_url_cuota = config.get('SeccionApi', 'api_personas_url_cuota')
    api_personas_url_seccion = config.get('SeccionApi', 'api_personas_url_seccion')
    api_personas_url_nivel = config.get('SeccionApi', 'api_personas_url_nivel')
    api_personas_url_grado = config.get('SeccionApi', 'api_personas_url_grado')


def listar():
    headers = CaseInsensitiveDict()
    headers["Accept"] = "application/json"
    headers["Content-Type"] = "application/json"

    datos = { }
    
    r = requests.get(api_personas_url_listar, headers=headers, json=datos)
    if (r.status_code == 200):
        # Validar response
        listado = r.json()
        for item in listado:
            print( "      " + str(item) )
    else:
        print( "Error " + str(r.status_code))


def crear(cedula: int, nombre: str, telefono: str, nivelEducacion:str, grado:int):
    headers = CaseInsensitiveDict()
    headers["Accept"] = "application/json"
    headers["Content-Type"] = "application/json"

    datos = {'cedula': cedula, 
             'nombre' : nombre,
             'telefono' : telefono,
             'nivelEducacion': nivelEducacion,
             'grado': grado
            }
    
    r = requests.post(api_personas_url_crear, headers=headers, json=datos)
    if (r.status_code >= 200 and r.status_code < 300):
        # Validar response
        print(r)
        print(r.text)
        
    else:
        print( "Error " + str(r.status_code))
        print(r.text)

def asignarSeccion(cedula:str):
    headers = CaseInsensitiveDict()
    headers["Accept"] = "application/json"
    headers["Content-Type"] = "application/json"

    url = api_personas_url_seccion.replace('[cedula]', str(cedula))
    
    r = requests.post(url, headers=headers)
    if (r.status_code >= 200 and r.status_code < 300):
        # Validar response
        print(r)
        print(r.text)
        
    else:
        print( "Error " + str(r.status_code))
        print(r.text)

def obtenerNivel(cedula:str):
    headers = CaseInsensitiveDict()
    headers["Accept"] = "application/json"
    headers["Content-Type"] = "application/json"

    #Url con parametros
    url = api_personas_url_nivel.replace('[cedula]', str(cedula))
    
    r = requests.get(url, headers=headers)
    if (r.status_code >= 200 and r.status_code < 300):
        # Validar response
        print(r)
        print(r.text)
        
    else:
        print( "Error " + str(r.status_code))
        print(r.text)

def obtenerGrado(cedula:str):
    headers = CaseInsensitiveDict()
    headers["Accept"] = "application/json"
    headers["Content-Type"] = "application/json"

    url = api_personas_url_grado.replace('[cedula]', str(cedula))
    
    r = requests.get(url, headers=headers)
    if (r.status_code >= 200 and r.status_code < 300):
        # Validar response
        print(r)
        print(r.text)
        
    else:
        print( "Error " + str(r.status_code))
        print(r.text)

def resetCuota(cedula:str):
    headers = CaseInsensitiveDict()
    headers["Accept"] = "application/json"
    headers["Content-Type"] = "application/json"

    url = api_personas_url_cuotareset.replace('[cedula]', str(cedula))
    
    r = requests.post(url, headers=headers)
    if (r.status_code >= 200 and r.status_code < 300):
        # Validar response
        print(r)
        print(r.text)
        
    else:
        print( "Error " + str(r.status_code))
        print(r.text)

def pagoCuota(cedula:str, monto:int):
    headers = CaseInsensitiveDict()
    headers["Accept"] = "application/json"
    headers["Content-Type"] = "application/json"

    url = api_personas_url_cuota.replace('[cedula]', str(cedula))
    datos = monto
    
    r = requests.post(url, headers=headers, json=datos)
    if (r.status_code >= 200 and r.status_code < 300):
        # Validar response
        print(r)
        print(r.text)
        
    else:
        print( "Error " + str(r.status_code))
        print(r.text)
#######################################################
######  Procesamiento principal
#######################################################

print("Iniciando " + datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S"))
cargar_variables() 
print(api_personas_url_seccion)
#Menu
while(True):
    print("1. Listar estudiantes")
    print("2. Crear nuevo estudiante")
    print("3. Asignar seccion")
    print("4. Obtener nivel de educación")
    print("5. Obtener grado")
    print("6. Pago de cuota")
    print("7. Salir")
    opcion = input("Ingrese opcion: ")

    if (opcion == "1"):
        listar()
        #Esperar enter
        input("Presione enter para continuar")
    elif (opcion == "2"):
        cedula = int(input("Ingrese cedula: "))
        nombre = input("Ingrese nombre: ")
        telefono = input("Ingrese telefono: ")
        nivelEducacion = input("Ingrese nivel de educacion: ")
        grado = int(input("Ingrese grado: "))
        crear(cedula, nombre, telefono, nivelEducacion, grado)
    elif (opcion == "3"):
        cedula = int(input("Ingrese cedula: "))
        asignarSeccion(cedula)
    elif (opcion == "4"):
        cedula = int(input("Ingrese cedula: "))
        obtenerNivel(cedula)
    elif (opcion == "5"):
        cedula = int(input("Ingrese cedula: "))
        obtenerGrado(cedula)
    elif (opcion == "6"):
        cedula = int(input("Ingrese cedula: "))
        monto = int(input("Ingrese monto: "))
        pagoCuota(cedula, monto)
    elif (opcion == "7"):
        print("Finalizando " + datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S"))
        sys.exit()
    else:
        print("Opcion no valida")



