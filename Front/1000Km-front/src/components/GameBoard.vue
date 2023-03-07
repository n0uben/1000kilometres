<script>
import axios from "axios";
import router from "@/router";

export default {
  name: "GameBoard",
  data () {
    return {
      pioche: [],
      main: [],
      defosse: [],
    }
  },
  mounted () {
    this.pioche.push({"id":1,"nom":"Véhicule prioritaire","effet":"prioritaire","nbdispo":1,"img":"prio.PNG"});
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
    async get() {
      let result = await axios.get("http://localhost:8080/utilisateur");
      console.warn(result);
      if (result.status == 200) {
        this.users = result.data;
        this.users.forEach(function (item) {//on parcours les users de la BDD
          let pseudoSaisi = document.getElementById("pseudo").value;
          if (pseudoSaisi == item["pseudo"]) {//si user trouvé
            let mdpSaisi = document.getElementById("mdp").value;
            if (mdpSaisi == item["motDePasse"]) {//si mdp OK
              console.log("connexion réussie")
              router.push({path: '/'})//redirection accueil
            }
          }
        });
      }
    },
    displaycard(){
      const pathimgs = "src/assets/images/Cartes/"
      for(let i = 0;i<this.main.length;i++){
        console.log("src/assets/images/Cartes/"+this.main[i]["img"]);
        document.getElementById("carte"+(i+1)).src=pathimgs+this.main[i]["img"];
      }
      document.getElementById("cartepioche").src=pathimgs+this.pioche[this.pioche.length-1]["img"];
      document.getElementById("cartedefosse").src=pathimgs+this.defosse[this.defosse.length-1]["img"];
    },

    dragstart(e){
      console.log("drag "+e.target.src);
      e.dataTransfer.effectAllowed="move";
      e.dataTransfer.setData("text",e.target.getAttribute("id"));
    },

    dropDefosse(e){
      const pathimgs = "src/assets/images/Cartes/"
      console.log("dropped "+e.target.src);
      let cartedefosse = document.getElementById("cartedefosse");
      cartedefosse.src="";
      var splitted = e.target.src.split('/');
      console.log(pathimgs+splitted[splitted.length-1]);
      cartedefosse.src=pathimgs+splitted[splitted.length-1];

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
        <img id="cartepioche" src="">
      </div>
      <div class="col-6 offset-1" id="main">
<<<<<<< HEAD
        <img class="carte" src="../assets/images/Cartes/100kmh.PNG"/>
        <img class="carte" style="margin-left: 2%" src="../assets/images/Cartes/50kmh.PNG"/>
        <img class="carte" style="margin-left: 2%" src="../assets/images/Cartes/citerne.PNG"/>
        <img class="carte" style="margin-left: 2%" src="../assets/images/Cartes/25kmh.PNG"/>
        <img class="carte" style="margin-left: 2%" src="../assets/images/Cartes/crevaison.PNG"/>
        <img class="carte" style="margin-left: 2%" src="../assets/images/Cartes/200kmh.PNG"/>
=======
        <img id="carte1" src="" draggable="true" >
        <img id="carte2" src="" draggable="true" >
        <img id="carte3" src="" draggable="true" >
        <img id="carte4" src="" draggable="true" >
        <img id="carte5" src="" draggable="true">
        <img id="carte6" src="" draggable="true" >
>>>>>>> main
      </div>
      <div class="col-2 offset-1" id="defosse" @dragover="dragover($event)" @drop="dropDefosse($event)">
        <img id="cartedefosse" src="">
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

#main img{
  width: 15%;
  margin-left: 1%;
}

#defosse{
  background-color: indianred;
}

</style>