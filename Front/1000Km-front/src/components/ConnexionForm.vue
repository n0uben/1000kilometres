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



    <!--    ---------------------- -->
    <!--    ---------------------- -->
    <!--    ---------------------- -->

<!--        <div class="row g-3">-->
<!--          <div class="offset-5 col-auto">-->
<!--            <label for="pseudo" class="col-form-label">Pseudo :</label>-->
<!--            <input type="text" id="pseudo" class="form-control">-->
<!--          </div>-->
<!--        </div>-->
<!--        <div class="row g-3 ">-->
<!--          <div class="offset-5 col-auto">-->
<!--            <label for="mdp" class="col-form-label">Mot de passe :</label>-->
<!--            <input type="password" id="mdp" class="form-control">-->
<!--          </div>-->
<!--        </div>-->
<!--        <div class="row g-3 my-3">-->
<!--          <div class="offset-5 col-auto">-->
<!--            <input type="submit" id="valider" class="form-control" value="Se connecter" @click="getUsers">-->
<!--          </div>-->
<!--        </div>-->
<!--        <div class="row g-3 my-3">-->
<!--          <div class="offset-5 col-auto">-->
<!--            Tu n'as pas de compte ? <router-link to="">Inscris toi !</router-link>-->
<!--          </div>-->
<!--        </div>-->

    <!--    ---------------------- -->
    <!--    ---------------------- -->
    <!--    ---------------------- -->

  </div>
</template>

<script>
import axios from "axios";
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
      userService.login(this.pseudo, this.motDePasse)
    }
  }
}
</script>

<style scoped>

</style>