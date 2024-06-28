import React from "react";
import { motion } from "framer-motion";

const loaderVariants = {
  spin: {
    rotate: [0, 360],
    borderRadius: ["25%","30%", "50%", "50%", "30%", "25%"],
    transition: {
      duration: 2,
      ease: "linear",
      repeat: Infinity,
    },
  },
};

const Loader = () => (
  <motion.div
    style={{
      display: "block",
      width: "40px",
      height: "40px",
      border: "3px solid",
      borderColor: "#3535f1",
    }}
    variants={loaderVariants}
    animate="spin"
  />
);

export default Loader;