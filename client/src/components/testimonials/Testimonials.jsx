import React from "react";

import {
  Box,
  Flex,
  Heading,
  Text,
  Stack,
  Container,
  Avatar,
  useColorModeValue,
} from "@chakra-ui/react";

const Testimonial = (props) => {
  const { children } = props;

  return <Box>{children}</Box>;
};

const TestimonialContent = (props) => {
  const { children } = props;

  return (
    <Stack
      bg={useColorModeValue("white", "gray.800")}
      boxShadow={"lg"}
      p={8}
      rounded={"xl"}
      align={"center"}
      pos={"relative"}
      _after={{
        content: `""`,
        w: 0,
        h: 0,
        borderLeft: "solid transparent",
        borderLeftWidth: 16,
        borderRight: "solid transparent",
        borderRightWidth: 16,
        borderTop: "solid",
        borderTopWidth: 16,
        borderTopColor: useColorModeValue("white", "gray.800"),
        pos: "absolute",
        bottom: "-16px",
        left: "50%",
        transform: "translateX(-50%)",
      }}
    >
      {children}
    </Stack>
  );
};

const TestimonialHeading = (props) => {
  const { children } = props;

  return (
    <Heading as={"h3"} fontSize={"xl"}>
      {children}
    </Heading>
  );
};

const TestimonialText = (props) => {
  const { children } = props;

  return (
    <Text
      textAlign={"center"}
      color={useColorModeValue("gray.600", "gray.400")}
      fontSize={"sm"}
    >
      {children}
    </Text>
  );
};

const TestimonialAvatar = ({ src, name, title }) => {
  return (
    <Flex align={"center"} mt={8} direction={"column"}>
      <Avatar src={src} mb={2} />
      <Stack spacing={-1} align={"center"}>
        <Text fontWeight={600}>{name}</Text>
        <Text fontSize={"sm"} color={useColorModeValue("gray.600", "gray.400")}>
          {title}
        </Text>
      </Stack>
    </Flex>
  );
};

export default function Testimonials() {
  return (
    <Box bg={useColorModeValue("gray.100", "gray.700")}>
      <Container maxW={"7xl"} py={16} as={Stack} spacing={12}>
        <Stack spacing={0} align={"center"}>
          <Heading>Our Users Speak</Heading>
          <Text>We have been working with users around the world</Text>
        </Stack>
        <Stack
          direction={{ base: "column", md: "row" }}
          spacing={{ base: 10, md: 4, lg: 10 }}
        >
          <Testimonial>
            <TestimonialContent>
              <TestimonialHeading>Sender's Testimonial</TestimonialHeading>
              <TestimonialText>
                "Sending gifts to my family overseas was always a hassle until I
                found Wasl. The chat feature made it easy to communicate with
                the traveler, and the entire process was smooth. My gifts
                arrived on time and in perfect condition. Highly recommended!"
              </TestimonialText>
            </TestimonialContent>
            <TestimonialAvatar
              src={"https://i.pravatar.cc/300"}
              name={"Ahmed Mohsen"}
              title={"Sender from Tunisia"}
            />
          </Testimonial>
          <Testimonial>
            <TestimonialContent>
              <TestimonialHeading>Mindblowing Service</TestimonialHeading>
              <TestimonialText>
                "As a frequent traveler, joining Wasl was a game-changer. The
                platform's user-friendly interface and secure payment system
                gave me peace of mind. Chatting with senders before the trip
                allowed me to understand their needs better. Great experience!"
              </TestimonialText>
            </TestimonialContent>
            <TestimonialAvatar
              src={"https://i.pravatar.cc/400"}
              name={"Jane Cooper"}
              title={"Traveler from Berlin"}
            />
          </Testimonial>
          <Testimonial>
            <TestimonialContent>
              <TestimonialHeading>Mindblowing Service</TestimonialHeading>
              <TestimonialText>
                "I've used Wasl multiple times, and each
                experience has been fantastic. The chat feature made
                coordinating deliveries a breeze. The travelers were punctual,
                and my packages arrived safely. I appreciate the efficiency and
                reliability of this service."
              </TestimonialText>
            </TestimonialContent>
            <TestimonialAvatar
              src={"https://i.pravatar.cc/500"}
              name={"Marshall Mccoy"}
              title={"Sender at ABC Corporation"}
            />
          </Testimonial>
        </Stack>
      </Container>
    </Box>
  );
}
