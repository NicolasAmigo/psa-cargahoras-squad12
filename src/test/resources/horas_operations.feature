Feature: las horas se guardan correctamente
  Scenario: Se guarda correctamente una hora
    When se crea un nuevo registro de recurso 10
    Then se gurdara el nuevo registro de recurso 10

  Scenario: Se modifica correctamente una hora
    Given un registro de recurso 11 y tarea 12
    When se modica la tarea a 11
    Then se guardara la tarea como 11

  Scenario: Se elimina correctamente una hora
    Given un registro de recurso
    When se elimina el registro
    Then no existira el registro