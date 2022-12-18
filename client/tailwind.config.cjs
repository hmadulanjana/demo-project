/** @type {import('tailwindcss').Config} */

const colors = require("tailwindcss/colors")

module.exports = {
  content: [
    "./index.html",
    "./src/**/*.{js,ts,jsx,tsx}",
  ],
  darkMode: "media",
  theme: {
    extend: {
      colors: {
        // gray: colors.amber,
        // red: colors.rose,
        // turquoise: "#40e0d0",
        // yellow: {
        //   light: "#feca1d",
        //   DEFAULT: "#e1ad01",
        //   dark: "#b48a01"
        // }
      }
    },
  },
  variants: {
    extend: {},
  },
  plugins: [
    require('tailwind-scrollbar'),
  ],
}
