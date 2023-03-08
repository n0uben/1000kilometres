<script>
import userService from "@/services/UserService";

export default {
  name: "InscriptionForm",
  data () {
    return {
      pseudo: '',
      motDePasse: '',
    }
  },
  methods: {
    handleSubmit(event) {
      console.log(this.pseudo + ' / ' + this.motDePasse)
      userService.inscription(this.pseudo, this.motDePasse).then(user => location.assign("/"))
    },

    onChangeConfirmer(e){
      var confirmer = e.target;
      var btn = document.getElementById("valider");
      if(this.motDePasse!=confirmer.value){
        confirmer.style.borderColor="red";
        btn.disabled=true;
      }else{
        confirmer.style.borderColor="green";
        btn.disabled=false;
      }
    }
  }
}
</script>

<template>
  <div class="container">
    <div class="row align-items-center mt-5">
      <div class="col">
        <h2 class="text-center">Inscription</h2>
      </div>
    </div>

    <div class="row">

      <div class="col">
        <form @submit.prevent="handleSubmit($event)" style="max-width: 400px; margin: auto">
          <div class="form-group">
            <label for="pseudo" class="col-form-label">Pseudo :</label>
            <input type="text" v-model="pseudo" id="pseudo" name="pseudo" class="form-control" />
          </div>

          <div class="form-group">
            <label for="motDePasse" class="col-form-label">Mot de passe : </label>
            <input type="password" v-model="motDePasse" id="motDePasse" name="motDePasse" class="form-control" />
          </div>
          <div class="form-group">
            <label for="confirme" class="col-form-label">Confirmer le mot de passe : </label>
            <input type="password" id="confirme" name="confirme" class="form-control" @keyup="onChangeConfirmer($event)"/>
          </div>

          <div class="form-group mt-4">
            <input type="submit" id="valider" class="btn btn-primary" value="Valider" disabled>
          </div>
        </form>
      </div>
    </div>
  </div>

</template>

<style scoped>

</style>