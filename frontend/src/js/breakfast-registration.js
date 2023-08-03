function registerBreakfast(event) {
    event.preventDefault()

    const date = $('#register-breakfast-date').val()
    let today = new Date()
    
    let givenDate = new Date(date)

    if (givenDate <= today) {
        $("#out-form").text('A data deve ser maior do que a atual.')
        $("#out-form").css('color', 'yellow')
        return
    }

    let breakfast = {}
    breakfast['date'] = date
    let body = JSON.stringify(breakfast)

    $.ajax({
        url: "https://desafio-unidac-backend-7fc18a667ec2.herokuapp.com/breakfast",
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