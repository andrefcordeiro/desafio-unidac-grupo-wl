let breakfastId

function onPageLoadFunction(event) {
    event.preventDefault()
    
    breakfastId = document.location.href.split('?')[1]
    console.log(document.location.href)

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
                        <li id="participation" 
                            class="list-group-item mb-4 d-flex flex-column align-items-center p-4">

                            <div class="d-flex flex-row">
                                <p class="p-2 text-primary font-weight-bold"> Nome:</p>
                                <p class="p-2">${e.employeeName}</p>
                                <p class="p-2 text-primary font-weight-bold"> CPF:</p>
                                <p class="p-2">${e.employeeCpf} </p>
                            </div>

                            <p class="text-primary font-weight-bold"> Marque as opções que foram trazidas: </p>

                            <form id="food-options-${e.employeeId}" 
                                    class="p-2 form-check d-flex flex-column" 
                                    onsubmit="onSumitted(this, event)">

                                <button id="update-food-was-brought-status" 
                                    type="submit" class="btn btn-primary mt-4 align-self-center" 
                                    style="width: 100%">
                                    Atualizar infos
                                </button>

                            </form>
                        </li>
                        `
                    )

                    // opções de comida selecionadas
                    e.foodOptions.forEach(foodOption => {
                        if (foodOption.foodOptionWasBrought === true)
                            checked_field = "checked"
                        else
                            checked_field = ""
                        
                        console.log(checked_field)
                        $("#food-options-" + e.employeeId).prepend(
                            `
                            <div>
                                <input class="form-check-input" type="checkbox" value="" id="food-option-${foodOption.id}" ${checked_field}>
                                <label class="form-check-label" for="food-option-checkbox">
                                ${foodOption.foodName}
                                </label>
                            </div>
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

