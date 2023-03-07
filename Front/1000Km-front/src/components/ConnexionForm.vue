<template>
  <div class="container">

    <div class="row align-items-center mt-5">
      <div class="col">
        <h2 class="text-center">Connexion</h2>
      </div>
    </div>

    <div class="row">

      <div class="col">
        <form @submit.prevent="handleSubmit" style="max-width: 400px; margin: auto">
          <div class="form-group">
            <label for="pseudo" class="col-form-label">Pseudo :</label>
            <input type="text" v-model="pseudo" id="pseudo" name="pseudo" class="form-control" />
          </div>

          <div class="form-group">
            <label for="motDePasse" class="col-form-label">Mot de passe : </label>
            <input type="password" v-model="motDePasse" id="motDePasse" name="motDePasse" class="form-control" />
          </div>

          <div class="form-group mt-4">
            <input type="submit" class="btn btn-primary" value="Se connecter">
          </div>
        </form>
      </div>
    </div>

  </div>
</template>

<script>
import router from "@/router";
import userService from "@/services/UserService";

export default {
  name: "ConnexionForm",
  data () {
    return {
      users: [],
      pseudo: '',
      motDePasse: '',
      returnUrl: ''
    }
  },
  mounted () {

  },
  methods: {
    handleSubmit(event) {
      console.log(this.pseudo + ' / ' + this.motDePasse)
      userService.login(this.pseudo, this.motDePasse).then(user => location.assign("/"))
    }
  }
}
</script>

<style scoped>

</style>