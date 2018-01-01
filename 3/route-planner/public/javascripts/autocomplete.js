
// attaching the autocomplete behaviour to the inputText elements 'From' and 'To'
$("#to, #from").autocomplete({
    source: function(request, response){
       $.ajax(jsRoutes.controllers.RoutePlanner.stations(request.term))
            .done(function(data){
                response($.map(data, function(item){
                    return { label: item, value: item}
                }))
            })
           .fail(function(error){console.log(error)});
    },
    minLength: 2
 });