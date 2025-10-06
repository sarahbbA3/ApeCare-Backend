# 🏥 Sistema de Gestión del Dispensario Médico (ApeCare)

## 📋 Descripción
El **Sistema de Gestión del Dispensario Médico (ApeCare)** es una aplicación que permite administrar de forma eficiente la información del dispensario médico de UNAPEC.

## 🧱 Tecnologías Utilizadas

### 🔹 Backend
- **Java 21**
- **Spring Boot 3.5**
- **Spring Data JPA**
- **Spring Web**
- **Spring Boot DevTools**
- **MySQL Driver**
- **Lombok**

## ⚙️ Configuración del proyecto

1. Clonar el repositorio
```bash
git clone https://github.com/sarahbbA3/ApeCare-Backend.git
```
2. Cambiar credenciales MySQL.

Navegar al archivo ```application.properties.example``` dentro de la carpeta ```resources```.

Modificar las lineas, por tus credenciales propias de MySQL:

```declarative
spring.datasource.username=NombreUsuario //Seguramente estan usando root
spring.datasource.password=Contraseña
```
