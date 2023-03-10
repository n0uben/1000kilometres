<script>
import CreateGameAnonyme from "@/components/CreateGameAnonyme.vue";
import CreateGameAuth from "@/components/CreateGameAuth.vue";
import carteService from "@/services/CarteService";

export default {
  name: "CreateGame",
  components: {CreateGameAnonyme,CreateGameAuth},
  data() {
    return {
      isAuth: false,
      current: null,
      imgnames: ["205.jpg","skyline.jpg"]
    }
  },
  props: {
    username: String
  },
  methods: {
    onClickAuth(event){

      if(event.currentTarget.id!=this.current){//si on n'a pas cliqué deux fois de suite sur la meme div
        this.isAuth=!this.isAuth;
        if(this.isAuth){
          let auth=document.getElementById("auth")
          auth.style.backgroundColor="#5f5f5f";
          auth.style.color="white";
          let perso=document.getElementById("perso");
          let anonym = document.getElementById("anonyme");
          anonym.style.backgroundColor="white";
          anonym.style.color="black";
        }else{
          let auth=document.getElementById("auth")
          auth.style.backgroundColor="white";
          auth.style.color="black";
          let perso=document.getElementById("perso");
          let anonym = document.getElementById("anonyme");
          anonym.style.backgroundColor="#5f5f5f";
          anonym.style.color="white";
        }
        this.current=event.currentTarget.id;//id le la div dernierement cliquée
      }
    },
    testApi(event) {
      carteService.getAll().then(response => console.log(response.data))
    }
  }
}
</script>

<template>
  <div class="container border border-dark" id="blockCreate">
    <div class="row border-bottom border-dark align-items-center">
      <div id="anonyme" class="col-6 py-3" style="background-color: #5f5f5f;color: white" @click="onClickAuth($event)">
        <h2 class="text-center">En mode Anonyme</h2>
      </div>
      <div id="auth" class="col-6 py-3" @click="onClickAuth($event)">
        <h2 class="text-center">Authentification</h2>
      </div>
    </div>
    <div class="row align-items-center" id="perso">
      <CreateGameAnonyme v-if="!isAuth"/>
      <CreateGameAuth :username="username" v-if="isAuth"/>
    </div>
    <div class="row align-items-center" id="start" v-if="!isAuth">
      <div class="col-12 text-center">
        <button id="demarrer">Démarrer</button>
      </div>
    </div>

  </div>
</template>



<style scoped>
#blockCreate{
  margin-top: 8%;

}

#perso{
  height: 400px;
}

#start{
  height: 100px;

}

#demarrer{
  background-color: lawngreen;
  font-size: 20px;
  font-weight: bold;
  padding: 1% 3% 1% 3%;
  width: auto;
  height: auto;

}

</style>