import {useEffect, useState} from "react";
import Link from "next/link";
import {Alignment, Button, ButtonGroup, Card, Elevation, FormGroup, Icon, InputGroup} from "@blueprintjs/core";
import Loader from "../../components/Loader";
import styles from "../../styles/Subscription.module.scss";

export default function SubscriptionIndex() {
  const defaultFormData = {name: '', url: ''};
  const [subscriptions, setSubscriptions] = useState(null);
  const [form, setForm] = useState(defaultFormData);

  const getSubscriptions = async () => {
    const response = await fetch(`${process.env.NEXT_PUBLIC_API_BASE}/subscription`, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json'
      }
    });
    return response.json();
  };

  const handleChange = (e) => {
    e.preventDefault();
    setForm({
      ...form,
      [e.target.name]: e.target.value
    });
  }

  const formValidate = (name, url) => {
    return name === '' || url === '' || !/^http[s]?:\/\/(.)+/.test(url);
  };

  const handleFormSubmit = async (e) => {
    e.preventDefault();
    const response = await fetch(`${process.env.NEXT_PUBLIC_API_BASE}/subscription`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(form)
    });

    response.json().then((subscription) => {
      setForm(defaultFormData);
    });
  };

  useEffect(() => {
    getSubscriptions().then(data => {
      setSubscriptions(data);
    });
    return () => {
      setSubscriptions(null);
    };
  }, []);

  return <main>
    <h2>Subscription Page Demo</h2>

    {subscriptions ?
      subscriptions.length > 0 ?
        <div className={styles.subscriptionsContainer}>
          {subscriptions.map(subscription => (
            <Card key={`sub-${subscription.id}`} className={styles.subscription} elevation={Elevation.ONE}>
              <Link href={`/subscription/${subscription.id}`}>
                <a>{subscription.name}</a>
              </Link>
              <div className={styles.subscriptionControls}>
                <section>
                  <ButtonGroup alignText={Alignment.CENTER} large={true} fill={true} minimal={true}>
                    <Button icon="edit"/>
                    <Button icon="list"/>
                    <Button icon="trash"/>
                  </ButtonGroup>
                </section>
              </div>
            </Card>
          ))}</div> : <div>No subscriptions</div> :
      <Loader text="Loading..."/>}

    <h2>Form</h2>

    <form onSubmit={handleFormSubmit}>
      <FormGroup label="Name">
        <InputGroup name="name" onChange={handleChange} value={form.name} leftElement={<Icon icon="bookmark"/>}/>
      </FormGroup>
      <FormGroup label="URL">
        <InputGroup name="url" onChange={handleChange} value={form.url} leftElement={<Icon icon="globe-network"/>}/>
      </FormGroup>
      <Button type="submit" intent="primary" disabled={formValidate(form.name, form.url)}>New Subscription</Button>
    </form>

  </main>
}