<script>
export default {
  name: "Attente",
  data() {
    return {
      pseudo: JSON.parse(localStorage.getItem('user')).pseudo,
      nbJoueurs: 1,
    }
  },

  mounted() {
    this.setJoueurs();
  },

  methods: {
    setJoueurs(){
      for(var i=1;i<=this.nbJoueurs;i++){
        document.getElementById("joueur"+i).style.backgroundColor="#cb5b5b";
      }
    },
    onClickLaunch(){
      location.assign("/game");
    }
  }
}
</script>

<template>
  <div class="container">

    <div class="row align-items-center mt-5">
      <div class="col">
        <h2 class="text-center">Joueurs</h2>
      </div>
    </div>

    <div class="row align-items-center">

      <div class="col text-center">
        <p id="joueur1" class="mt-4 py-2" style="background-color: #7e3d3d;width: 20%;margin-left: 40%;border-radius: 5px;">{{this.pseudo}}</p>
        <p id="joueur1" class="mt-4 py-2" style="background-color: #7e3d3d;width: 20%;margin-left: 40%;border-radius: 5px;">En attente...</p>
        <p id="joueur1" class="mt-4 py-2" style="background-color: #7e3d3d;width: 20%;margin-left: 40%;border-radius: 5px;">En attente...</p>
        <p id="joueur1" class="mt-4 py-2" style="background-color: #7e3d3d;width: 20%;margin-left: 40%;border-radius: 5px;">En attente...</p>
        <button class="py-2 px-3 mx-3 mt-4">Inviter</button><button class="py-2 px-3 mx-3" @click="onClickLaunch">Lancer</button>
      </div>
    </div>

  </div>

</template>

<style scoped>
button{
  background-color: lawngreen;
  font-size: 16px;
  font-weight: bold;

}
</style>