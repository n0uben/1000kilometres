<script>
import router from "@/router";

export default {
  name: "GameBoard",
  data () {
    return {
      pioche: [],
      main: [],
      defosse: [],
      imgCard: "",//image de la carte
      carteDragged: null,//carte prise dans la pioche
      pathImgs: "src/assets/images/Cartes/",
      droppedCards: [],//cartes de la main posées dans la défosse
    }
  },
  mounted () {
    this.pioche.push({"id":12,"nom":"25 Km","effet":"km","nbdispo":4,"img":"25kmh.PNG"});
    this.pioche.push({"id":1,"nom":"Véhicule prioritaire","effet":"prioritaire","nbdispo":1,"img":"prio.PNG"});
    this.pioche.push({"id":20,"nom":"200 Km","effet":"km","nbdispo":4,"img":"200kmh.PNG"});
    this.main.push({"id":2,"nom":"As du volant","effet":"as","nbdispo":1,"img":"as.PNG"});
    this.main.push({"id":20,"nom":"200 Km","effet":"km","nbdispo":4,"img":"200kmh.PNG"});
    this.main.push({"id":20,"nom":"200 Km","effet":"km","nbdispo":4,"img":"200kmh.PNG"});
    this.main.push({"id":20,"nom":"200 Km","effet":"km","nbdispo":4,"img":"200kmh.PNG"});
    this.main.push({"id":20,"nom":"200 Km","effet":"km","nbdispo":4,"img":"200kmh.PNG"});
    this.main.push({"id":10,"nom":"Réparations","effet":"reparation","nbdispo":3,"img":"reparations.PNG"});
    this.defosse.push({"id":11,"nom":"Accident","effet":"accident","nbdispo":2,"img":"accident.PNG"});
    this.displaycard();
  },
  methods: {
    displaycard(){
      for(let i = 0;i<this.main.length;i++){
        console.log("src/assets/images/Cartes/"+this.main[i]["img"]);
        document.getElementById("carte"+(i+1)).src=this.pathImgs+this.main[i]["img"];
      }
      document.getElementById("cartepioche").src=this.pathImgs+this.pioche[this.pioche.length-1]["img"];
      document.getElementById("cartedefosse").src=this.pathImgs+this.defosse[this.defosse.length-1]["img"];
    },

    dragstart(e){//on recupére l'image de la carte prise dans la main
      console.log("dragstart"+e.target.src);
      this.carteDragged = e.target;
      this.imgCard=this.splitSrc(this.carteDragged.src);
    },

    dropDefosse(e){//on change le src de l'image de la defosse et on lui assigne le src de la carte prise dans la main
      let cartedefosse = document.getElementById("cartedefosse");
      cartedefosse.src="";
      cartedefosse.src=this.pathImgs+this.imgCard;
      this.carteDragged.src="";
      this.droppedCards.push(this.carteDragged);//
    },

    splitSrc(src){//recupere nom de l'image dans le path
      var splitted = src.split('/');
      return splitted[splitted.length-1];
    },

    piocheCarte(e){
      let cartepioche = document.getElementById("cartepioche");
      console.log("pioche : "+this.pathImgs+this.splitSrc(e.target.src));
      if(this.droppedCards.length>0){
        this.droppedCards[this.droppedCards.length-1].src=this.pathImgs+this.splitSrc(e.target.src);
        this.droppedCards.pop();
        this.pioche.pop();
        cartepioche.src=this.pathImgs+this.pioche[this.pioche.length-1]["img"];
      }

    },

    dragover(e){
      console.log("dragover");
      e.preventDefault();
    }
  }
}
</script>
<template>
  <div class="container" id="roads">
    <div class="row">
      <img class="player" src="../assets/images/skyline_sprite.png"/>
      <div class="road">
      </div>
    </div>
<!--    <div class="row ">-->
<!--      <img class="player" src="../assets/images/skyline_sprite.png"/>-->
<!--      <div class="road">-->

<!--      </div>-->
<!--    </div>-->
<!--    <div class="row ">-->
<!--      <img class="player" src="../assets/images/skyline_sprite.png"/>-->
<!--      <div class="road">-->

<!--      </div>-->
<!--    </div>-->
<!--    <div class="row ">-->
<!--      <img class="player" src="../assets/images/skyline_sprite.png"/>-->
<!--      <div class="road">-->

<!--      </div>-->
<!--    </div>-->
  </div>
  <div class="container" id="cards">
    <div class="row" id="linecards">
      <div class="col-2" id="pioche">
        <img id="cartepioche" src="" @click="piocheCarte($event)">
      </div>
      <div class="col-6 offset-1" id="main">
        <img id="carte1" src="" draggable="true" @dragstart="dragstart($event)">
        <img id="carte2" src="" draggable="true" @dragstart="dragstart($event)">
        <img id="carte3" src="" draggable="true" @dragstart="dragstart($event)">
        <img id="carte4" src="" draggable="true" @dragstart="dragstart($event)">
        <img id="carte5" src="" draggable="true" @dragstart="dragstart($event)">
        <img id="carte6" src="" draggable="true" @dragstart="dragstart($event)">
      </div>
      <div class="col-2 offset-1" id="defosse" @dragover="dragover($event)" @drop="dropDefosse($event)">
        <img id="cartedefosse" src="" style="width: 60%">
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

#main img{
  width: 15%;
  margin-left: 1%;
}

#defosse{
  background-color: indianred;
}

</style>