import {createRouter, createWebHistory} from "vue-router";
import ConnexionForm from "@/components/ConnexionForm.vue";
import CreateGame from "@/components/CreateGame.vue"
import GameBoard from "@/components/GameBoard.vue";
import InscriptionForm from "@/components/InscriptionForm.vue";

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
        },
        {
            path: '/inscription',
            component: InscriptionForm
        }


    ]
})

export default router;
