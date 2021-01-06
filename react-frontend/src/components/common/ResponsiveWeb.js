import React from 'react';
import styled from 'styled-components';

const ResponsiveWebBlock = styled.div`
  padding-left: 1rem;
  padding-right: 1rem;
  width: 1024px;
  margin: 0 auto;

  @media (max-width: 1024px) {
    width: 768px;
  }
  @media (max-width: 768px) {
    width: 100%;
  }
`;

const ReponsiveWeb = ({ children, ...rest }) => {
  return <ResponsiveWebBlock {...rest}>{children}</ResponsiveWebBlock>;
};

export default ReponsiveWeb;
