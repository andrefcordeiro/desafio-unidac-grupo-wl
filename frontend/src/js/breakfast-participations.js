function onPageLoadFunction(event) {
    event.preventDefault()

    $.ajax({
        url: "http://localhost:8080/breakfast",
        dataType: "json",
        type: 'GET',
        success: function (response) {

            response.forEach(breakfast => {

                let yearMonthDay = breakfast.date.split('-')
                let formattedDate = yearMonthDay[2].concat('/', yearMonthDay[1]).concat('/', yearMonthDay[0])

                $("#breakfasts").append(
                    `
                    <a href="./breakfast-participation.html?${breakfast.id}">
                        <p class="h2">
                        ${breakfast.id}. ${formattedDate}
                        </p class="h2"> 
                    </a>
                    `
                )
            });
        },
    });
}

