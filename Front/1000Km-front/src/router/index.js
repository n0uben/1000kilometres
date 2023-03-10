import {createRouter, createWebHistory} from "vue-router";
import ConnexionForm from "@/components/ConnexionForm.vue";
import CreateGame from "@/components/CreateGame.vue"
import GameBoard from "@/components/GameBoard.vue";
import InscriptionForm from "@/components/InscriptionForm.vue";
import Attente from "@/components/Attente.vue";
import Logout from "@/components/Logout.vue";

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
        },
        {
            path: '/attente',
            component: Attente
        },
        {
            path: '/logout',
            component: Logout
        },


    ]
})

export default router;
