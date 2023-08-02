function registerBreakfast(event) {
    event.preventDefault()

    var breakfast = {}
    breakfast['date'] = $('#register-breakfast-date').val()
    var body = JSON.stringify(breakfast)
    $.ajax({
        url: "http://localhost:8080/breakfast",
        contentType: "application/json",
        data: body,
        dataType: "json",
        type: 'POST',
        success: function (response) {
            $("#out-form").text('Café da manhã cadastrado.')
            $("#out-form").css('color', 'white')
        },
        error:  function (response) {
            if(response.responseText.includes('Resource already exists. date:')){
                $("#out-form").text('Café da manhã já cadastrado para esta data.')
                $("#out-form").css('color', 'yellow')
            }
            else {
                $("#out-form").text(response.responseText)
                $("#out-form").css('color', 'yellow')
            } 
        },
    })
}