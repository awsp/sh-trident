import {useEffect, useState} from "react";
import Link from "next/link";
import {Button, FormGroup, Icon, InputGroup} from "@blueprintjs/core";

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
      console.log(subscription);
      setForm(defaultFormData);
    });
  };

  useEffect(() => {
    getSubscriptions().then(data => {
      setSubscriptions(data);
    });
  });

  return <main>
    <h2>Subscription Page Demo</h2>

    <form onSubmit={handleFormSubmit}>
      <FormGroup label="Name">
        <InputGroup name="name" onChange={handleChange} value={form.name} leftElement={<Icon icon="bookmark" />}/>
      </FormGroup>
      <FormGroup label="URL">
        <InputGroup name="url" onChange={handleChange} value={form.url} leftElement={<Icon icon="globe-network" />}/>
      </FormGroup>
      <Button type="submit" intent="primary" disabled={formValidate(form.name, form.url)}>New Subscription</Button>
    </form>

    <h3>List</h3>
    <ul>
      {subscriptions ? subscriptions.map(subscription => (
        <li>
          <Link href={`/subscription/${subscription.id}/focus`}>
            <a>{subscription.name}</a>
          </Link>
        </li>
      )) : ''}
    </ul>
  </main>
}