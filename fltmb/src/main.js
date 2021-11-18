import "./global.css";
import HMR from "@roxi/routify/hmr";
import {registerSW} from 'virtual:pwa-register';
import App from "./App.svelte";

const updateSW = registerSW({
  onNeedRefresh() {},
  onOfflineRead() {},
});

const app = HMR(App, { target: document.body }, "routify-app");

export default app;