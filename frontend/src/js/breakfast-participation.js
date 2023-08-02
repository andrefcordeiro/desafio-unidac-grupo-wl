let breakfastId

function onPageLoadFunction(event) {
    event.preventDefault()
    
    breakfastId = document.location.href.split('?')[1]

    $.ajax({
        url: "http://localhost:8080/breakfast/" + breakfastId + "/participations",
        dataType: "json",
        type: 'GET',
        success: function (response) {
            if(response.length == 0) {
                $("#out-form").text('Não há nenhuma participação cadastrada para este café da manhã')
            }
            else {
                response.forEach(e => {
                    // dados do participante
                    $("#participations").append(
                        `
                        <div class="container-fluid text-center" id="participation">
                            <div class="d-flex flex-row">
                                <p class="p-2"> Nome do colaborador: ${e.employeeName}</p>
                                <p class="p-2"> Cpf do colaborador: ${e.employeeCpf} </p>
                            </div>

                            <p> Marque as opções que foram trazidas: </p>
                            <form id="food-options-${e.employeeId}" class="p-2 form-check" onsubmit="onSumitted(this, event)">
                                <button id="update-food-was-brought-status" type="submit" class="btn btn-primary">
                                Submit</button>
                            </form>
                        </div>
                        `
                    )

                    // opções de comida selecionadas
                    e.foodOptions.forEach(foodOption => {
                        $("#food-options-" + e.employeeId).prepend(
                            `
                            <input class="form-check-input" type="checkbox" value="" id="food-option-${foodOption.id}">
                            <label class="form-check-label" for="food-option-checkbox">
                                ${foodOption.foodName}
                            </label>
                            `
                        )
                    })
                });
            }
        },
    });
}

function updateFoodWasBroughtStatus(employeeId, foodOptionId, foodOptionWasBrought) {

    let body = JSON.stringify({foodOptionWasBrought: foodOptionWasBrought})

    $.ajax({
        url: "http://localhost:8080/breakfast/" + breakfastId + "/participations/" 
                + employeeId + "/foodOptions/" + foodOptionId + "/update-food-was-brought-status",
        contentType: "application/json",
        data: body,
        dataType: "json",
        type: 'POST',
        success: function (response) {
            $("#out-form").text('Informações cadastradas')
            $("#out-form").css('color', 'green')
        },
        error:  function (response) {
            $("#out-form").text('Erro ao cadastrar informações')
            $("#out-form").css('color', 'red')
        },
    })
}

function onSumitted(employeeForm, event) {
    event.preventDefault()

    // pegando o id do formulario específico daquele colaborador
    const formId = employeeForm.id 
    const employeeId = employeeForm.id.split("-")[2]

    // percorrendo todos os checkboxes marcados daquele form
    $("#" + formId + " input:checkbox:checked").each(function() {
        const foodOptionId = $(this)[0].id.split("-")[2]

        updateFoodWasBroughtStatus(employeeId, foodOptionId, foodOptionWasBrought=true)
    })
    
    // percorrendo todos checkboxes NÃO marcados daquele form
    $("#" + formId + " input:checkbox:not(:checked)").each(function() {
        const foodOptionId = $(this)[0].id.split("-")[2]

        updateFoodWasBroughtStatus(employeeId, foodOptionId, foodOptionWasBrought=false)
    })
}

