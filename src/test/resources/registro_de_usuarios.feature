Feature: Registro de Usuarios
  Con el fin de administrar mis productos bancarios
  Yo como usuario quiero poder registrarme
  Para poder realizar pagos y ejecutar operaciones sobre mis productos

  Scenario: Registro exitoso de usuario
    Given Josimar es un cliente que quiere poder administrar sus productos bancarios
    When el envia la información requerida para el registro
    Then el debe obtener una cuenta virtual para poder ingresar cuando lo requiera