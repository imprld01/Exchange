$("#test1 li").on("click", function(){
  var meterItems = $("#test1 li");
  var targetValue = $(this).data("value"); // Find value of target item. Used below as parameter in loop
  var arr = $.makeArray( meterItems ); // Had to create array of <li> to loop through them

  meterItems.removeClass("active"); // Clears prior active states

  for (i = 0; i <= targetValue - 1; i++) {
      $(arr[i]).addClass("active");
  }

});
$("#test2 li").on("click", function(){
  var meterItems = $("#test2 li");
  var targetValue = $(this).data("value"); // Find value of target item. Used below as parameter in loop
  var arr = $.makeArray( meterItems ); // Had to create array of <li> to loop through them

  meterItems.removeClass("active"); // Clears prior active states

  for (i = 0; i <= targetValue - 1; i++) {
      $(arr[i]).addClass("active");
  }

});
$("#test3 li").on("click", function(){
  var meterItems = $("#test3 li");
  var targetValue = $(this).data("value"); // Find value of target item. Used below as parameter in loop
  var arr = $.makeArray( meterItems ); // Had to create array of <li> to loop through them

  meterItems.removeClass("active"); // Clears prior active states

  for (i = 0; i <= targetValue - 1; i++) {
      $(arr[i]).addClass("active");
  }

});
$("#test4 li").on("click", function(){
  var meterItems = $("#test4 li");
  var targetValue = $(this).data("value"); // Find value of target item. Used below as parameter in loop
  var arr = $.makeArray( meterItems ); // Had to create array of <li> to loop through them

  meterItems.removeClass("active"); // Clears prior active states

  for (i = 0; i <= targetValue - 1; i++) {
      $(arr[i]).addClass("active");
  }

});
$("#test5 li").on("click", function(){
  var meterItems = $("#test5 li");
  var targetValue = $(this).data("value"); // Find value of target item. Used below as parameter in loop
  var arr = $.makeArray( meterItems ); // Had to create array of <li> to loop through them

  meterItems.removeClass("active"); // Clears prior active states

  for (i = 0; i <= targetValue - 1; i++) {
      $(arr[i]).addClass("active");
  }

});
