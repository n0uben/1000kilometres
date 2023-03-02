<script>
export default {
  name: "ConnexionForm"
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
        <input type="submit" id="valider" class="form-control" value="Se connecter">
      </div>
    </div>
  </div>
</template>

<style scoped>

</style>