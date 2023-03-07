<script>
export default {
  name: "GameBoard"
}
</script>

<template>
  <div class="container" id="roads">
    <div class="row">
      <img class="player1" src="../assets/images/skyline_sprite.png"/>
      <div class="road">
      </div>
    </div>
    <div class="row ">
      <img class="player2" src="../assets/images/skyline_sprite.png"/>
      <div class="road">

      </div>
    </div>
    <div class="row ">
      <img class="player3" src="../assets/images/skyline_sprite.png"/>
      <div class="road">

      </div>
    </div>
    <div class="row ">
      <img class="player4" src="../assets/images/skyline_sprite.png"/>
      <div class="road">

      </div>
    </div>
  </div>
  <div class="container" id="cards">
    <div class="row" id="linecards">
      <div class="col-2" id="pioche">

      </div>
      <div class="col-6 offset-1" id="main">
        <img class="carte" src="../assets/images/Cartes/100kmh.PNG"/>
        <img class="carte" style="margin-left: 2%" src="../assets/images/Cartes/50kmh.PNG"/>
        <img class="carte" style="margin-left: 2%" src="../assets/images/Cartes/citerne.PNG"/>
        <img class="carte" style="margin-left: 2%" src="../assets/images/Cartes/25kmh.PNG"/>
        <img class="carte" style="margin-left: 2%" src="../assets/images/Cartes/crevaison.PNG"/>
        <img class="carte" style="margin-left: 2%" src="../assets/images/Cartes/200kmh.PNG"/>
      </div>
      <div class="col-2 offset-1" id="defosse">

      </div>
    </div>
  </div>
</template>

<style scoped>
#roads{
  margin-top: 4%;
}
.carte{
  width: 15% ;
  margin-top: 6%;
}
.player1{
  width: 7%;
  margin-left: 4%;
}
.player2{
  width: 7%;
  margin-left: 94%;
}
.player3{
  width: 7%;
  margin-left: 50%;
}
.player4{
  width: 7%;
  margin-left: 10%;
}

.road{
  width: 90%;
  margin-left: 10%;
  height: 20px;
  background-color: green;
  margin-bottom: 5%;
}

#cards{

}

#linecards{
  height: 200px;
}

#pioche{
  background-color: lightgreen;
}

#main{
  background-color: grey;
  height: 100%;
}

#defosse{
  background-color: indianred;
}

</style>