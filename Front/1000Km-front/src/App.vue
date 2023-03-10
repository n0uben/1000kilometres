<script setup>
import NavBar from "@/components/NavBar.vue";

</script>
<script>
import userService from "@/services/UserService";

export default {
  name: "App",
  data() {
    return {
      currentUser: ""
    }
  },

  created() {
    this.currentUser = userService.currentuser()
  },

}
</script>

<template>
  <header>
    <NavBar :username="currentUser"/>
  </header>

  <body>
    <section id="create">
      <router-view :username="currentUser"/>
    </section>
  </body>
</template>

<style scoped>

</style>
