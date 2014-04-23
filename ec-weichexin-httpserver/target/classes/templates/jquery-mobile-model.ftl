
<!DOCTYPE html> 

<html> 

  <head> 

  <title>Collapsible Content Demo</title> 

  <link rel="stylesheet" href="http://code.jquery.com/mobile/1.0a1/jquery.mobile-1.0a1.min.css"  /> 

  <script src="http://code.jquery.com/jquery-1.4.3.min.js"></script> 

  <script src="http://code.jquery.com/mobile/1.0a1/jquery.mobile-1.0a1.min.js"></script> 
  
  <script type="text/javascript">
  $(function() {
 	
 	});
  </script>

</head> 

<body> 

  

<div data-role="page" id="home"> 

  


  

  <div data-role="content"> 

    <div data-role="collapsible" data-state="collapsed"> 

      <h3>About this app</h3> 

      <p>This app rocks!</p> 

    </div> 

   <a href="#" data-role="button" data-theme="a">About this app</a> 

    <a href="#" data-role="button" data-theme="b">About this app</a> 

    <a href="#" data-role="button" data-theme="c">About this app</a> 

    <a href="#" data-role="button" data-theme="d">About this app</a> 

    <a href="#" data-role="button" data-theme="e">About this app</a> 
  

 <form action="#" method="get"> 

    

     <div data-role="fieldcontain"> 

       <label for="name">Your Name:</label> 

       <input type="text" name="name" id="name" value=""   /> 

     </div> 

     

     <div data-role="controlgroup"> 

       <legend>Which flavour(s) would you like?</legend> 

       

       <input type="checkbox" name="vanilla" id="vanilla" class="custom"  /> 

       <label for="vanilla">Vanilla</label> 

       

       <input type="checkbox" name="chocolate" id="chocolate" class="custom"  /> 

       <label for="chocolate">Chocolate</label> 

       

       <input type="checkbox" name="strawberry" id="strawberry" class="custom"  /> 

       <label for="strawberry">Strawberry</label> 

       

     </div>    

     

     <div data-role="fieldcontain"> 

       <label for="quantity">Number of Cones:</label> 

       <input type="range" name="quantity" id="quantity" value="1" min="1" max="10"   /> 

     </div> 

  

     <div data-role="fieldcontain"> 

       <label for="sprinkles">Sprinkles:</label> 

        <select name="sprinkles" id="sprinkles" data-role="slider"> 

          <option value="off">No</option> 

          <option value="on">Yes</option> 

        </select> 

     </div> 

  

     <div data-role="fieldcontain"> 

       <label for="store">Collect from Store:</label> 

        <select name="store" id="store"> 

          <option value="mainStreet">Main Street</option> 

          <option value="libertyAvenue">Liberty Avenue</option> 

          <option value="circleSquare">Circle Square</option> 

          <option value="angelRoad">Angel Road</option> 

        </select> 

     </div> 

     

    <div class="ui-body ui-body-b"> 

      <fieldset class="ui-grid-a"> 

        <div class="ui-block-a"><button type="submit" data-theme="d">Cancel</button></div> 

        <div class="ui-block-b"><button type="submit" data-theme="a">Order Ice Cream</button></div>     

      </fieldset> 

    </div> 

     

  


  

    <h2 style="padding: 1em 0;">A list view</h2> 

  

    <ul data-role="listview" data-inset="true"> 

      <li>Cat</li> 

      <li>Dog</li> 

      <li>Mouse</li> 

      <li>Squirrel</li> 

    </ul> 

    

    <h2 style="padding: 1em 0;">A list of links</h2> 

  

    <ul data-role="listview" data-inset="true"> 

      <li><a href="#">About this app</a></li> 

      <li><a href="#">Buy ice cream</a></li> 

      <li><a href="#">Find a store</a></li> 

    </ul> 

  

    <h2 style="padding: 1em 0;">Nested lists</h2> 

  

    <ul data-role="listview" data-inset="true"> 

      <li>Play 

        <ul> 

          <li><a href="#">Easy</a></li> 

          <li><a href="#">Medium</a></li> 

          <li><a href="#">Hard</a></li> 

        </ul> 

      </li> 

      <li>Settings 

        <ul> 

          <li><a href="#">Graphics</a></li> 

          <li><a href="#">Sound</a></li> 

          <li><a href="#">Device</a></li> 

        </ul> 

      </li> 

      <li>Highscores 

        <ul> 

          <li><a href="#">View</a></li> 

          <li><a href="#">Submit</a></li> 

          <li><a href="#">Reset</a></li> 

        </ul> 

      </li> 

    </ul> 

  

    <h2 style="padding: 1em 0;">A split button list with filter</h2> 

  

    <ul data-role="listview" data-inset="true" data-filter="true"> 

      <li> 

        <a href="#">The Grapes of Wrath</a> 

        <a href="#">Buy This Book</a> 

      </li> 

      <li> 

        <a href="#">The Trial</a> 

        <a href="#">Buy This Book</a> 

      </li> 

      <li> 

        <a href="#">A Tale of Two Cities</a> 

        <a href="#">Buy This Book</a> 

      </li>      

    </ul> 

  

    <h2 style="padding: 1em 0;">A list with count bubbles</h2> 

  

    <ul data-role="listview" data-inset="true"> 

      <li><a href="#">SuperWidgets</a> <span class="ui-li-count">14</span></li> 

      <li><a href="#">MegaWidgets</a> <span class="ui-li-count">0</span></li> 

      <li><a href="#">WonderWidgets</a> <span class="ui-li-count">327</span></li>      

    </ul> 


</div> 

  

</body> 

</html> 