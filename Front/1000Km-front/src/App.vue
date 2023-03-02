<script setup>
import HelloWorld from './components/HelloWorld.vue'
import TheWelcome from './components/TheWelcome.vue'
import NavBar from "@/components/NavBar.vue";
import CreateGame from "@/components/CreateGame.vue";
</script>

<template>
  <header>
    <NavBar/>
  </header>

  <body>
    <section id="create">
      <router-view/>
    </section>
  </body>
</template>

<style scoped>

</style>
