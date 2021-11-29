import {svelte} from '@sveltejs/vite-plugin-svelte';
import {defineConfig} from 'vite';

export default defineConfig({
  server: {
    host: '0.0.0.0',
    port: 5000,
  },

  plugins: [svelte()],
});