<script>
export default {
  name: "CreateGameAnonyme",
  data() {
    return {
      imgnames: ["205.jpg","skyline.jpg"]
    }
  },
  methods: {
    onClickReroll(){
      let img = document.getElementById("car");
      var current = img.src.split("/")[img.src.split("/").length-1];
      var rand = Math.floor(Math.random() * this.imgnames.length);
      while(this.imgnames[rand]==current){
        rand = Math.floor(Math.random() * this.imgnames.length);
      }
      img.src="src/assets/images/"+this.imgnames[rand];

    }
  }
}
</script>

<template>
  <div class="col-6 text-center">
    <img id="car" src="./../assets/images/205.jpg"/><br>
    <img id="reroll" src="./../assets/images/reroll.png" @click="onClickReroll()"/><br><br>
  </div>
  <div class="col-6 text-center">
    <label for="perso" style="font-size: 25px;font-weight: bold">Votre Pseudo :</label><br><br>
    <input type="text" id="pseudo"/>
  </div>
</template>



<style scoped>
#car{
  width: 50%;

}

#reroll{
  margin-left: 55%;
}

</style>