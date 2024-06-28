import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [react()],
  server: {
    host: '0.0.0.0', // Listen on all network interfaces
    strictPort: true,
    port: parseInt(process.env.PORT, 10) || 8080, // Use the PORT environment variable if it's available
  }
})