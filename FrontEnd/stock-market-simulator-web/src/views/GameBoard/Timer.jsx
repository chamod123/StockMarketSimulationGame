import React, { useState, useEffect } from 'react';

export default function Timer(props) {
  const [count, setCount] = useState(props.startCount);

  useEffect(() => {
    const interval = setInterval(() => {
      setCount(count + 1)
    }, 1000);
    return () => clearInterval(interval);

  });

  return (
    <h3 className={props.className}>{count} seconds</h3>
  );
}