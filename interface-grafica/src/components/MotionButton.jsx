import React from 'react';
import { motion } from 'framer-motion';

const MotionButton = ({ onClick, children, color }) => (
  <motion.button
    whileHover={{ scale: 1.05 }}
    transition={{ duration: 0.2 }}
    onClick={onClick}
    style={{ backgroundColor: color }}
  >
    {children}
  </motion.button>
);

export default MotionButton;