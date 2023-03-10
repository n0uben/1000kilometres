<template>

</template>

<script>
import userService from "@/services/UserService";

export default {
  name: "Logout",
  created() {
    userService.logout();
  }
}
</script>

<style scoped>

</style>