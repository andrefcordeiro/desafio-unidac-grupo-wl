function registerEmployee(event) {
    event.preventDefault()
    var employee = {} 
    employee['name'] = $('#register-employee-name').val()
    employee['cpf'] = $('#register-employee-cpf').val()

    var body = JSON.stringify(employee)
    $.ajax({
        url: "http://localhost:8080/employee",
        contentType: "application/json",
        data: body,
        dataType: "json",
        type: 'POST',
        success: function (response) {
            $("#out-form").text('Colaborador cadastrado.')
            $("#out-form").css('color', 'white')
        },
        error:  function (response) {
            if(response.responseText.includes('Resource already exists. cpf:')){
                $("#out-form").text('Colaborador com este CPF já cadastrado.')
                $("#out-form").css('color', 'yellow')
            }
            else {
                $("#out-form").text(response.responseText)
                $("#out-form").css('color', 'yellow')
            } 
        },
  })
}