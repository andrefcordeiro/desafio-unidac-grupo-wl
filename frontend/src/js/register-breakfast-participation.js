let foodOptions = []

function onPageLoadFunction() {
    // recebe todas as datas de café da manhã existentes
    $.ajax({
        url: "http://localhost:8080/breakfast",
        dataType: "json",
        type: 'GET',
        success: function (response) {
            response.forEach(breakfastDate => {
                let yearMonthDay = breakfastDate.date.split('-')
                let formattedDate = yearMonthDay[2].concat('-', yearMonthDay[1]).concat('-', yearMonthDay[0])

                $('#breakfast-dates').append('<option value="' + breakfastDate.id + '">' + formattedDate + '</option>')
            });
        },
    });

    // recebe todas as opções de comida
    $.ajax({
        url: "http://localhost:8080/breakfast/food-options",
        dataType: "json",
        type: 'GET',
        success: function (response) {
            response.forEach(foodOption => {
                $('#options-selected').append('<option value="' + foodOption.id + '">' + foodOption.foodName + '</option>')
            })
            foodOptions = response
        },
    });
}

// funções usadas para selecionar opções de comida
const selectedOptions = [];

function addFoodOption() {
    const foodName = $("#options-selected option:selected").text()
    const selectedOption = $("#options-selected").val();

    if (selectedOption) {
        selectedOptions.push({id: selectedOption, foodName: foodName});
        updateSelectedOptionsList();
    }
}

function removeFoodOption() {
    selectedOptions.pop();
    updateSelectedOptionsList();
}

function updateSelectedOptionsList() {
    const selectedOptionsList = $("#food-options-list");
    selectedOptionsList.empty();

    selectedOptions.forEach(option => {
        selectedOptionsList.append(
        `
            <li class="list-group-item text-primary font-weight-bold mt-2">
                ${option.foodName}
            </li>
        `);
    });
}

// enviar a requisição
function registerBreakfastParticipation(event) {
    event.preventDefault()

    if(selectedOptions.length == 0) {
        $("#out-form").text('Selecione uma opção de alimento')
        $("#out-form").css('color', 'yellow')
        return
    }

    let breakfastId = $("#breakfast-dates").val()

    let foodOptionsIds = []
    selectedOptions.forEach(option => {
        foodOptionsIds.push(option.id)
    })

    let breakfastParticipation = {
        'employeeCpf': $("#register-employee-cpf").val(),
        'foodOptionsIds': foodOptionsIds
    }

    var body = JSON.stringify(breakfastParticipation)

    $.ajax({
        url: "http://localhost:8080/breakfast/" + breakfastId + "/participations",
        contentType: "application/json",
        data: body,
        dataType: "json",
        type: 'POST',
        success: function (response) {
            $("#out-form").text('Participação cadastrada')
            $("#out-form").css('color', 'white')
        }, 
        error: function (response) {
            let foodOptionAlreadySelectedId = response.responseJSON.message.split("id")[1].split(" ")[1]
            let foodOptionAlreadySelectedName = foodOptions[foodOptionAlreadySelectedId - 1].foodName
            
            $("#out-form").text('Opção "' +  foodOptionAlreadySelectedName + '" já foi selecionada. Escolha outro alimento.')
            $("#out-form").css('color', 'yellow')          
        },
    })
}
