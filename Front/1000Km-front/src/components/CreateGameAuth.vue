<script>
export default {
  name: "CreateGameAuth",
  props: {
    username: String
  },
  methods: {
    onClickConnecter(){
      location.assign("/connexion");
    },
    onClickInscription(){
      location.assign("/inscription");
    },
    onClickDemarrer(){
      location.assign("/attente");
    }
  }

}
</script>

<template>
  <div v-if="username==null" class="col-6 text-center">
    <button @click="onClickConnecter" id="connecter" class="btnauth">Se Connecter</button>
  </div>
  <div v-if="username==null" class="col-6 text-center">
    <button @click="onClickInscription" id="creer" class="btnauth">Créer un compte</button>
  </div>
  <div v-if="username!=null" class="col text-center">
    <button @click="onClickDemarrer" id="demarrer" class="btnauth">Démarrer</button>
  </div>
</template>



<style scoped>
.btnauth{
  background-color: lawngreen;
  font-size: 20px;
  font-weight: bold;
  padding: 2% 6% 2% 6%;
}
</style>