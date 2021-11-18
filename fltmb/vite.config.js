import {svelte} from '@sveltejs/vite-plugin-svelte';
import {VitePWA} from 'vite-plugin-pwa';
import {defineConfig} from 'vite';

export default defineConfig({
  server: {
    host: '0.0.0.0',
    port: 5000,
  },

  plugins: [
    svelte(),
    VitePWA({
      includeAssets: [],
      manifest: {
        name: 'FLT-MB',
        short_name: 'FLT-MB',
        description: 'FLT - Material Burst',
        theme_color: '#ffffff',
        icons: []
      },
      workbox: {
        cleanupOutdatedCaches: true
      }
    })
  ],
});