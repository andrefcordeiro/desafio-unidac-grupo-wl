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

                // prod
                $("#breakfasts").append(
                    `
                    <li class="list-group-item mb-4">
                        <a class="text-primary" href="src/pages/breakfast-participations.html?${breakfast.id}">
                            ${formattedDate}
                        </a>
                    </li>
                    `
                )
            
                // dev
                // $("#breakfasts").append(
                //     `
                //     <li class="list-group-item mb-4">
                //         <a class="text-primary" href="/src/pages/breakfast-participations.html?${breakfast.id}">
                //             ${formattedDate}
                //         </a>
                //     </li>
                //     `
                // )
            });
        },
    });
}

