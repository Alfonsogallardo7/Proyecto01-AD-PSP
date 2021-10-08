# Proyecto01-AD-PSP-Grupo2_Alfonso-Daniel-Javier-Manuel

## **TRIANAFY**

_Nuestro proyecto consiste en la implementación de una API REST para gestionar información musical, una especie de Spotify

## MODELO :boom:

!

- ### **Artista** _Puede ser autor o grupo musical_ :microphone:

    - Identificador(id)
    - Nombre

- ### **Canción** :cd:
    - Identificador(id)
    - Título
    - **Artista**
    - Álbum
    - Año

-  ### **Playlist** :twisted_rightwards_arrows:

    - Identificador(id)
    - nombre
    - **Lista de Canciones**
    - descripción

## **FUNCIONALIDADES** :mag_right:

## Artista :microphone:

### **Añadir nuevo artista**
Podrás añadir un artista a la base de datos ingresando todos sus datos
---
### **Lista de Artistas**
Se mostrarán todos los artistas registrados 
---
### **Buscar artista por id**
Podrás buscar un artista en particular escribiendo su id en la barra de búsqueda `/artist/{id}`
---
### **Modificar un artista**
Solo podrás modificar su nombre escribiendo su id como antes
---
### **Borrar artista**
Se eliminará al artista de la base de datos 
---
## Canciones :cd:

### **Añadir nueva canción**
Podrás añadir una canción a la base de datos ingresando todos sus datos
---
### **Lista de canciones**
Se mostrarán todos las canciones registradas 
---
### **Ver datos de una canción en particular**
Podrás buscar todos los datos de una canción en particular escribiendo su id en la barra de búsqueda `/songs/{id}`
---
### **Modificar una canción**
Podrás modificar todos los datos de una canción `/songs/{id}` en el caso de cambiar el artista que la posee deberás escribir su nombre
---
### **Borrar canción**
Se eliminará la canción de la base de datos
--- 
    
## Playlist :twisted_rightwards_arrows:

### **Añadir nueva playlist**
Podrás añadir una playlist a la base de datos ingresando todos sus datos _nombre_ y _descripción_
---
### **Lista de playlist**
Se mostrarán todos las playlist registradas y el número de canciones que contienen
---
### **Ver datos de una playlist en particular**
Podrás buscar todos los datos de una playlist en particular escribiendo su id en la barra de búsqueda, además podrás ver datos de las canciones que contienen `/list/{id}`
---
### **Modificar una playlist**
Podrás modificar los datos de una playlist _nombre_ y _descripción_ `/list/{id}` 
---
### **Borrar playlist**
Se eliminará la playlist de la base de datos `/list/{id}` 
---     
### **Añadir canción a playlist**
Podrás añadir una cación a una lista detallando los ids de ambas `/list/{id1}/songs/{id2}` 
---
### **Ver todas las canciones**
Podrás ver una lista de todas las canciones de la playlist con el id que indiques `/list/{id}` 
---     
### **Mostrar canción de una playlist**
Podrás ver una canción que indicas por su id de una playlist que también indicas por id. Deberás escribir algo así  `/list/{id1}/songs/{id2}` 
---
### **Borrar canción de playlist**
Podrás borrar una canción de una playlist en particular siempre indicanco el id de la playlist y el id de la canción. `/list/{id1}/songs/{id2}`  _Esto no eliminará la canción de la base de datos totalmente_
