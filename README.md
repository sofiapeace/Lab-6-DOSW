## Integrantes:
Gina Sofia Garcia
Diego Andres Ortiz
Julio Cesar Mayorquin
Hildebrando Peña

# Library Project - Laboratorio TDD

## Descripción
Sistema de gestión de bibliotecas desarrollado con **TDD (Test-Driven Development)** como fundamento de estructuración técnica. El proyecto aplica pruebas unitarias, análisis de cobertura con JaCoCo y análisis estático con SonarQube para garantizar la calidad del código.

## Tecnologías Utilizadas
- **Java 21**
- **Maven**
- **JUnit 5** - Pruebas unitarias
- **JaCoCo** - Cobertura de código (mínimo 85%)
- **SonarQube** - Análisis estático (Docker)

## Resultados de Cobertura

| Paquete | Cobertura |
|---------|-----------|
| edu.eci.dosw.tdd.library | 97% |
| edu.eci.dosw.tdd.book | 84% |
| edu.eci.dosw.tdd.loan | 78% |
| edu.eci.dosw.tdd.user | 82% |
| **TOTAL** | **87%** |

## Pruebas Unitarias (24 pruebas)

### addBook (4 pruebas)
- `addBook_ShouldReturnTrue_WhenBookIsNew`
- `addBook_ShouldReturnFalse_WhenBookHasNoIsbn`
- `addBook_ShouldReturnFalse_WhenBookIsNull`
- `addBook_ShouldIncreaseCount_WhenSameBookAddedMultipleTimes`

### loanABook (4 pruebas)
- `shouldFailWhenUserAlreadyHasAnActiveLoanForTheSameBook`
- `shouldFailWhenUserIsNotFound`
- `shouldFailWhenBookIsNotFound`
- `loanABook_ShouldReturnNull_WhenBookNotAvailable`

### returnLoan (4 pruebas)
- `returnLoan_ShouldReturnLoanWithReturnedStatus_WhenLoanExists`
- `returnLoan_ShouldReturnNull_WhenLoanNotInList`
- `returnLoan_ShouldIncreaseBookAvailability_WhenLoanReturned`
- `fullLoanAndReturnFlow_ShouldWorkCorrectly`

### addUser (4 pruebas)
- `addUser_ShouldReturnTrue_WhenUserIsValid`
- `addUser_ShouldReturnFalse_WhenUserIsNull`
- `addUser_ShouldReturnFalse_WhenUserIdIsNull`
- `addUser_ShouldReturnFalse_WhenUserIdIsEmpty`

### Validación (4 pruebas)
- `validateUser_ShouldReturnNull_WhenUserNotFound`
- `validateUser_ShouldReturnUser_WhenUserExists`
- `validateBook_ShouldReturnNull_WhenBookNotFound`
- `validateBook_ShouldReturnBook_WhenBookExists`

### Book (4 pruebas)
- `book_Equals_ShouldReturnTrue_WhenSameIsbn`
- `book_Equals_ShouldReturnFalse_WhenDifferentIsbn`
- `book_Equals_ShouldReturnFalse_WhenNull`
- `book_HashCode_ShouldBeConsistentWithEquals`

## Configuración de SonarQube

### 1. Iniciar SonarQube con Docker
```bash
# Descargar imagen
docker pull sonarqube

# Ejecutar contenedor
docker run -d --name sonarqube \
  -p 9000:9000 \
  -e SONAR_ES_BOOTSTRAP_CHECKS_DISABLE=true \
  sonarqube:latest

# Verificar estado
docker ps -a
 
#Ejecutar sonarQube para ver pruebas
mvn clean test jacoco:report

mvn sonar:sonar -Dsonar.login=sqp_110d4da8904e5c192292ac46346156e9fd9b0381 (Donde el resial es el login que usa uno al ir a la url http://localhost:9000 y pedir tocken)
```
## Prueba fotográfica
![SonarQube Dashboard](https://github.com/sofiapeace/Lab-6-DOSW/blob/develop/images/sonarQube.png)
