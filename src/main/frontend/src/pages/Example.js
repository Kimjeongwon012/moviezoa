import React from "react";
import Header from "../components/Common/Header";
import Footer from "../components/Common/Footer";
import {Link} from 'react-router-dom';

function Example() {
    return (
        <div>
            <Header/>
            <main className={styles.mainContainer}>
                <div className={styles.mainContent}>
                </div>
            </main>
            <Footer/>
        </div>
    );
}

export default Example;