import React from "react";
import PaymentSuc from "../../components/paymenSucces/PaymentSuc";
import { useParams } from "react-router-dom";

export default function Payment() {
  const { sessionId } = useParams();
  return (
    <>
      <PaymentSuc sessionId={sessionId} />
    </>
  );
}
