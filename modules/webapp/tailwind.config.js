/** @type {import('tailwindcss').Config} */

const isProduction = process.env.NODE_ENV === "production";
const scalajsMode = isProduction ? "opt" : "fastopt";

export default {
  content: [
    "../../.bleep/builds/normal/.bloop/webapp/webapp-js/main.js",
    "./index.html",
  ],
  theme: {
    extend: {},
  },
  plugins: [require("@tailwindcss/forms")],
};
