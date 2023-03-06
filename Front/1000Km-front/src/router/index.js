import {createRouter, createWebHistory} from "vue-router";
import ConnexionForm from "@/components/ConnexionForm.vue";
import CreateGame from "@/components/CreateGame.vue"
import GameBoard from "@/components/GameBoard.vue";

const router = createRouter({
    history: createWebHistory(),
    routes: [
        {
            path: '/',
            component: CreateGame
        },
        {
            path: '/connexion',
            component:ConnexionForm
        },
        {
            path: '/game',
            component: GameBoard
        }

    ]
})

export default router;
