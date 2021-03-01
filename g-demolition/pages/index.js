import React from 'react';
import styles from '../styles/Dashboard.module.scss'
import {Controls} from "../components/controls";


export default function Dashboard({content}) {
  return (
    <div id="dashboard" className={styles.content}>
      <Controls />
      <div className={styles.feedList}>
      </div>
    </div>
  )
};

export async function getServerSideProps(context) {
  const res = await fetch('http://localhost:8081/api/v1/feed');
  const data = await res.json();
  return {
    props: data
  }
}