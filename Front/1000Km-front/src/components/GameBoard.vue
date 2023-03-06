<script>
export default {
  name: "GameBoard"
}
</script>

<template>
  <div class="container" id="roads">
    <div class="row">
      <img class="player" src="../assets/images/skyline_sprite.png"/>
      <div class="road">
      </div>
    </div>
    <div class="row ">
      <img class="player" src="../assets/images/skyline_sprite.png"/>
      <div class="road">

      </div>
    </div>
    <div class="row ">
      <img class="player" src="../assets/images/skyline_sprite.png"/>
      <div class="road">

      </div>
    </div>
    <div class="row ">
      <img class="player" src="../assets/images/skyline_sprite.png"/>
      <div class="road">

      </div>
    </div>
  </div>
  <div class="container" id="cards">
    <div class="row" id="linecards">
      <div class="col-2" id="pioche">

      </div>
      <div class="col-6 offset-1" id="main">

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

.player{
  width: 7%;
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
}

#defosse{
  background-color: indianred;
}

</style>