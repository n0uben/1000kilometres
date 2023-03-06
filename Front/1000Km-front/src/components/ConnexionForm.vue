<script>
import axios from "axios";
import router from "@/router";

export default {
  name: "ConnexionForm",
  data () {
    return {
      users: [],
    }
  },
  mounted () {

  },
  methods: {
    async getUsers(){
      let result = await axios.get("http://localhost:8080/utilisateur");
      console.warn(result);
      if(result.status==200){
        this.users = result.data;
        this.users.forEach(function(item){//on parcours les users de la BDD
          let pseudoSaisi = document.getElementById("pseudo").value;
          if(pseudoSaisi ==item["pseudo"]){//si user trouvé
            let mdpSaisi = document.getElementById("mdp").value;
            if(mdpSaisi==item["motDePasse"]){//si mdp OK
              console.log("connexion réussie")
              router.push({path: '/'})//redirection accueil
            }
          }
        });
      }
    },

  }
}
</script>

<template>
  <div class="container">
    <div class="row g-3 align-items-center mt-5">
      <div class="offset-5 col-auto">
        <h2 class="text-center">Connexion</h2>
      </div>

    </div>
    <div class="row g-3">
      <div class="offset-5 col-auto">
        <label for="pseudo" class="col-form-label">Pseudo :</label>
        <input type="text" id="pseudo" class="form-control">
      </div>
    </div>
    <div class="row g-3 ">
      <div class="offset-5 col-auto">
        <label for="mdp" class="col-form-label">Mot de passe :</label>
        <input type="password" id="mdp" class="form-control">
      </div>
    </div>
    <div class="row g-3 my-3">
      <div class="offset-5 col-auto">
        <input type="submit" id="valider" class="form-control" value="Se connecter" @click="getUsers">
      </div>
    </div>
    <div class="row g-3 my-3">
      <div class="offset-5 col-auto">
        <a>Tu n'as pas de compte ? </a><router-link to="">Inscris toi !</router-link>
      </div>
    </div>
  </div>
</template>

<style scoped>

</style>