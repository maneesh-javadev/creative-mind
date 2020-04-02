// Select the main list and add the class "hasSubmenu" in each LI that contains an UL
$('.treeMenuParent').each(function(){
  $this = $(this);
  $this.find(".treeMenuChild").has(".treeMenuParent").addClass("hasSubmenu");
});
// Find the last li in each level
$('.treeMenuChild:last-child').each(function(){
  $this = $(this);
  // Check if LI has children
  if ($this.children('.treeMenuParent').length === 0){
    // Add border-left in every UL where the last LI has not children
    $this.closest('.treeMenuParent').css("border-left", "1px solid gray");
  } else {
    // Add border in child LI, except in the last one
    $this.closest('.treeMenuParent').children(".treeMenuChild").not(":last").css("border-left","1px solid gray");
    // Add the class "addBorderBefore" to create the pseudo-element :defore in the last li
    $this.closest('.treeMenuParent').children(".treeMenuChild").last().children(".anchorChild").addClass("addBorderBefore");
    // Add margin in the first level of the list
    $this.closest('.treeMenuParent').css("margin-top","20px");
    // Add margin in other levels of the list
    $this.closest('.treeMenuParent').find(".treeMenuChild").children(".treeMenuParent").css("margin-top","20px");
  };
});
// Add bold in li and levels above
$('.treeMenuParent .treeMenuChild').each(function(){
  $this = $(this);
  $this.mouseenter(function(){
    $( this ).children("a").css({"font-weight":"bold","color":"#336b9b"});
  });
  $this.mouseleave(function(){
    $( this ).children("a").css({"font-weight":"normal","color":"#428bca"});
  });
});
// Add button to expand and condense - Using FontAwesome
$('.treeMenuParent .treeMenuChild.hasSubmenu').each(function(){
  $this = $(this);
  $this.prepend("<a href='#'><i class='fa fa-minus-circle'></i><i style='display:none;' class='fa fa-plus-circle'></i></a>");
  $this.children("a").not(":last").removeClass().addClass("toogle");
});
// Actions to expand and consense
$('.treeMenuParent .treeMenuChild .hasSubmenu a.toogle').click(function(){
  $this = $(this);
  $this.closest(".treeMenuChild").children(".treeMenuParent").toggle("slow");
  $this.children("i").toggle();
  return false;
});